package com.meta.service;


import com.meta.model.Like;

import java.util.Collection;

public abstract class AbstractLikeService<T> implements LinkedinService<T> {

   public abstract Collection<T> getAll(final Long commentId, final Long postId);
   public abstract boolean createLike(final Like like);
}
