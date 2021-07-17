package net.xdclass.service;

import net.xdclass.domain.Video;

public interface VideoService {

    Video findById(int videoId);

    Integer save(Video video);
}
