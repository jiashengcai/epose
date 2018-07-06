package com.engcorner.epose.repository.user;

import com.engcorner.epose.domain.user.UserPose;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("userPoseRepository")
public interface UserPoseRepository extends CrudRepository<UserPose, Long> {

}
