package com.ftbworld.dating.services;

import com.ftbworld.dating.domain.Like;
import com.ftbworld.dating.exceptions.DatingBadRequestException;
import com.ftbworld.dating.exceptions.DatingNotFoundException;
import com.ftbworld.dating.exceptions.DatingPermissionsException;
import com.ftbworld.dating.repositories.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LikeServiceImpl implements LikeService{
    @Autowired
    LikeRepository likeRepository;

    @Override
    public Like createLikeByUsernames(String actor, String username_b) {
        if (actor.equals(username_b)) {
            // We can handle this using SQL alone, but then we'd have 2 catches for unique exceptions.
            throw new DatingBadRequestException("Can't like yourself.");
        }

        Like like = likeRepository.createLikeByUsernames(actor, username_b);
        return like;
    }

    @Override
    public boolean deleteLikeByUsernames(String actor, String username_a, String username_b) {
        if (!actor.equals(username_a) && !actor.equals(username_b)) {
            throw new DatingPermissionsException("Cannot delete a like not related to you.");
        }

        return likeRepository.deleteLikeByUsernames(username_a, username_b);
    }

    @Override
    public List<Like> getLikesByUsername(String username_a) {
        List<Like> likes = likeRepository.getLikesByUsername(username_a);
        return likes;
    }

    @Override
    public List<Like> getLikesOfUsername(String username_b) {
        List<Like> likes = likeRepository.getLikesOfUsername(username_b);
        return likes;
    }

    @Override
    public List<Like> getMatchesOfUsername(String actor) {
        List<Like> likes = likeRepository.getMatchesOfUsername(actor);
        return likes;
    }
}
