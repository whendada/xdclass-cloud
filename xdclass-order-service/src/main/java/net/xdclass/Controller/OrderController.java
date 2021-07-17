package net.xdclass.Controller;

import net.xdclass.domain.Video;
import net.xdclass.domain.VideoOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("api/v1/video_order")
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping("save")
    public Object save(Integer videoId) {
        if (Objects.isNull(videoId)) {
            return null;
        }
        List<ServiceInstance> list = discoveryClient.getInstances("xdclass-video-service");
        ServiceInstance serviceInstance = null;
        if (!CollectionUtils.isEmpty(list)) {
            serviceInstance = list.get(0);
        }
        Video video = restTemplate.getForObject("http://" + serviceInstance.getHost() +":" + serviceInstance.getPort() +"/api/v1/video/find_by_id?videoId=" + videoId, Video.class);
        VideoOrder videoOrder = new VideoOrder();
        videoOrder.setVideoTitle(video.getTitle());
        videoOrder.setVideoId(video.getId());
        return video;
    }
}
