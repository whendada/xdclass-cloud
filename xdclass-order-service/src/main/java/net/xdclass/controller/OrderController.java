package net.xdclass.controller;

import net.xdclass.domain.Video;
import net.xdclass.domain.VideoOrder;
import net.xdclass.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("api/v1/video_order")
public class OrderController {

    @Autowired
    private VideoService videoService;

    @GetMapping("find_by_id")
    public Object findById(Integer videoId) {
        if (Objects.isNull(videoId)) {
            return null;
        }
        Video video = videoService.findById(videoId);
        VideoOrder videoOrder = new VideoOrder();
        videoOrder.setVideoTitle(video.getTitle());
        videoOrder.setVideoId(video.getId());
        videoOrder.setServerInfo(video.getServerInfo());
        return videoOrder;
    }

    @PostMapping("save")
    public Object save(@RequestBody Video video) {
        Integer result = videoService.save(video);
        Map<String, Integer> map = new HashMap<>();
        map.put("row", result);
        return map;
    }

    int temp = 0;
    @GetMapping("list")
    public Object findList() {
        ++temp;
        if (temp%3 == 0) {
            throw  new RuntimeException();
        }
//        TimeUnit.SECONDS.sleep(3);
        Map<String, String> map = new HashMap<>();
        map.put("title1", "SpringCloud微服务实战");
        map.put("title2", "xdclass大课");
        return map;
    }
}
