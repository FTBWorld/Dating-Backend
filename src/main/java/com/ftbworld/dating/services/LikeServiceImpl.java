package com.ftbworld.dating.services;

import com.ftbworld.dating.domain.Like;
import com.ftbworld.dating.exceptions.DatingBadRequestException;
import com.ftbworld.dating.exceptions.DatingNotFoundException;
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
    public Like createLike(int user_id, int liked_user) throws DatingBadRequestException {
        if (user_id == liked_user) {
            // We can handle this using SQL alone, but then we'd have 2 catches for unique exceptions.
            throw new DatingBadRequestException("Can't like yourself.");
        }

        Like like = likeRepository.createLike(user_id, liked_user);
        return like;
    }

    @Override
    public void deleteLike(int user_id, int like_id) throws DatingBadRequestException {

    }

    @Override
    public List<Like> getLikesByUser(int user_id) {
        List<Like> likes = likeRepository.getLikesByUser(user_id);
        return likes;
    }

    @Override
    public List<Like> getLikesOfUser(int user_id) {
        List<Like> likes = likeRepository.getLikesOfUser(user_id);
        return likes;
    }

    @Override
    public List<Like> getMatchesOfUser(int user_id) {
        return null;
    }

    @Override
    public Like getLikeById(int user_id, int like_id) throws DatingNotFoundException {
        return null;
    }
}
