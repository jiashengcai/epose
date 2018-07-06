

/*
document.write("<script src=\"js/tf.min.js\"></script>")
document.write("<script src=\"js/posenet.min.js\"></script>")
document.write("<script src=\"js/jquery-3.3.1.js\"></script>")
document.write("<script src=\"js/FileSaver.js\"></script>")
*/


var Epose = Epose || {};

Epose.onInited = async function () {

}

var setting = {
    confidence :{
        pose:0.12,
        part:0.5
    },
    videoWidth:600,
    videoHeight:500,
    scale:0.3,
    stride:16,
    fliph:false,
    net:null,
    video:null,
    ctx:null
};

async function loadVideo() {


    if (!navigator.mediaDevices || !navigator.mediaDevices.getUserMedia) {
        throw 'Browser API navigator.mediaDevices.getUserMedia not available';
    }
    var video=document.getElementById('video');
    video.width = setting.videoWidth;
    video.height = setting.videoHeight;
    const stream = await navigator.mediaDevices.getUserMedia({
        'audio':false,
        'video':{
            facingMode:'user',
            width:setting.videoWidth,
            height:setting.videoHeight
        }
    });

    video.srcObject = stream;
    video.onloadedmetadata= function () {
        video.play();
    };
    return video;
}

Epose.init = async function  () {
    console.log('加载模型...');
    posenet.checkpoints['1.01'].url = '/models/mobilenet_v1_101';
    console.log();
    setting.net = await posenet.load();
    console.log('加载摄像头...');
    setting.video = await loadVideo();

    var canvas =  document.getElementById('canvas');
    canvas.width = setting.videoWidth;
    canvas.height = setting.videoHeight;
    setting.ctx = canvas.getContext('2d');

    setting.ctx.save();
    setting.ctx.scale(-1, 1);
    setting.ctx.translate(-setting.videoWidth, 0);
    setting.ctx.drawImage(setting.video, 0, 0, setting.videoWidth, setting.videoHeight);
    setting.ctx.restore();
    setting.ctx.fillStyle = '#049d5a';
    Epose.onInited();
}


function toTuple({y, x}) {
    return [y, x];
}
/**
 * Draws a line on a canvas, i.e. a joint
 */
function drawSegment([ay, ax], [by, bx], color, scale, ctx) {
    ctx.beginPath();
    ctx.moveTo(ax * scale, ay * scale);
    ctx.lineTo(bx * scale, by * scale);
    ctx.lineWidth = 2;
    ctx.strokeStyle = color;
    ctx.stroke();
}

/**
 * Draws a pose skeleton by looking up all adjacent keypoints/joints
 */
function drawSkeleton(keypoints, minConfidence, ctx, scale = 1) {
    const adjacentKeyPoints = posenet.getAdjacentKeyPoints(
        keypoints, minConfidence);

    adjacentKeyPoints.forEach((keypoints) => {
        drawSegment(toTuple(keypoints[0].position),
            toTuple(keypoints[1].position), ctx.fillStyle, scale, ctx);
    });
}


function drawkps(kps, minConfidence, ctx) {
    var angles = getAngles(kps);

    for (var i = 0; i < kps.length; i++) {
        const kp = kps[i];

        if (kp.score < minConfidence) {
            continue;
        }
        var x = kp.position.x;
        var y = kp.position.y;

        var map = {};
        var off = 60;
        map["leftElbow"] = [0,off];
        map["leftShoulder"] = [2,off];
        map["leftHip"] = [4,off];
        map["leftKnee"] = [6,off];

        off = -off;
        map["rightElbow"] = [1,off];
        map["rightShoulder"] = [3,off];
        map["rightHip"] = [5,off];
        map["rightKnee"] = [7,off];

        if (map.hasOwnProperty(kp.part)) {
            off = map[kp.part][1];
            ctx.moveTo(x,y);
            ctx.lineTo(x+off,y);
            ctx.strokeStyle = "#ff692d";
            ctx.stroke();
            x+=off;
            if (off < 0 ) x -= 60;
            ctx.fillText("角度:"+angles[map[kp.part][0]]+"°",x,y);
        }
        switch (kp.part) {
            case "leftEye":
            case "rightEye":
            case "leftEar":
            case "rightEar":
                continue;
        }

        ctx.beginPath();
        ctx.arc(kp.position.x,kp.position.y , 3, 0, 2 * Math.PI);
        ctx.fillStyle = '#2c7bff';
        ctx.fill();
    }
    return angles;
}



function  Vector(x,y) {
    this.x = x;
    this.y = y;
    return this;
}


function getmod(vec1) {
    var ma = Math.sqrt(Math.pow(vec1.x,2) + Math.pow(vec1.y,2));
    return ma;
}

function getAngleCos(vec1,vec2) {
    var ma = getmod(vec1);
    var mb = getmod(vec2);
    var ab = vec1.x*vec2.x +  vec1.y*vec2.y;

    var cos = ab/(ma*mb);
    return cos;
}
function  getAngles(kps) {
    var ndx  = [5,7,9, 7,5,11, 5,11,13, 11,13,15];

    var res = [];

    var pi = 3.14;
    function xx(i, flag) {
        for (var j = 0 ; j < 3; j++){
            if (kps[ndx[i+j] + flag].score < setting.confidence.part) {
                res.push(-1);
                return;
            }
        }

        var vec1 = normalize(kps[ndx[i+1] + flag],kps[ndx[i] + flag]);
        var vec2 = normalize(kps[ndx[i+1] + flag],kps[ndx[i+2] + flag]);
        res.push((Math.acos(getAngleCos(vec1,vec2)) / pi*180).toFixed(2));
    }
    for (var i = 0 ; i < ndx.length; i+=3) {

        xx(i, 0);
        xx(i, 1);
    }
    return res;
}

function  normalize(kp1,kp2) {
    return new Vector(kp2.position.x - kp1.position.x, kp2.position.y - kp1.position.y);
}
function evaluate(cosset1,cosset2) {
    var sum = new Array();
    for(var i = 0 ; i< cosset1.length ; i++) {
        if (cosset1[i] < 0 || cosset2[i] < 0) {
            sum.push(-1);
            continue;
        }
        sum.push(((32400 - Math.pow(cosset1[i]-cosset2[i],2)) / 324).toFixed(2));
    }
    return sum;
}




