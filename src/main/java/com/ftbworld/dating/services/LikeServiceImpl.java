package com.ftbworld.dating.services;

import com.ftbworld.dating.domain.Like;
import com.ftbworld.dating.repositories.LikeRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LikeServiceImpl implements LikeService {

    @Autowired
    LikeRepository likeRepository;

    @Override
    public Like createLikeByUserIDs(String userID, String likedUserID) {
        return likeRepository.createLikeByUserIDs(userID, likedUserID);
    }

    @Override
    public List<Like> findLikesByUserID(String userID) {
        return likeRepository.findLikesByUserID(userID);
    }

    @Override
    public List<Like> findLikesOfUserID(String likedUserID) {
        return likeRepository.findLikesOfUserID(likedUserID);
    }

    @Override
    public Like findLikeByID(String id) {
        return likeRepository.findLikeByID(id);
    }

    @Override
    public boolean deleteLikeByID(String id) {
        return likeRepository.deleteLikeByID(id);
    }
}
