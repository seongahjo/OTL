package com.otl.liveapi.daemon;

import com.otl.liveapi.service.ServerStatusService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

import static com.otl.liveapi.controller.PlayerURLController.getAccessCount;

@Service
@RequiredArgsConstructor
@Slf4j
public class SchedulerService {
	@Value("${otl.threshold}")
	private int threshold;
	private final ServerStatusService serverStatusService;

	@Scheduled(cron = "0 0/1 * * * *")
	public void calculateCongestion() {
		AtomicInteger atomicCount = getAccessCount();
		int count = atomicCount.get();
		log.info("congestion {} threshold {} ", count, threshold);
		serverStatusService.setIsCongested(count > threshold);
		atomicCount.set(0);
	}
}
