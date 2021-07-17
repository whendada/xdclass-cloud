package net.xdclass.controller;

import net.xdclass.domain.Video;
import net.xdclass.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api/v1/video")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @GetMapping("find_by_id")
    public Object findById(int videoId, HttpServletRequest request){
        Video video = videoService.findById(videoId);
        video.setServerInfo(request.getServerName() + ":" + request.getServerPort());
        return video;
    }

    @PostMapping("save")
    public Integer save(@RequestBody Video video) {
        Integer result = videoService.save(video);
        return result;
    }

}
