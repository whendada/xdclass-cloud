package net.xdclass.service;

import net.xdclass.domain.Video;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "xdclass-video-service")
public interface VideoService {

    @GetMapping("api/v1/video/find_by_id")
    Video findById(@RequestParam("videoId") int videoId);

    @PostMapping("api/v1/video/save")
    Integer save(@RequestBody Video video);
}
