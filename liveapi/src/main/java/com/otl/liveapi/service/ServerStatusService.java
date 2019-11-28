package com.otl.liveapi.service;

import com.otl.liveapi.model.ServerStatus;
import com.otl.liveapi.repository.ServerStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ServerStatusService {

	private final ServerStatusRepository serverStatusRepository;

	public boolean getIsCongested() {
		return serverStatusRepository.findByKey(ServerStatus.SERVER_STATUS.IS_CONGESTED).isValue();
	}

	public void setIsCongested(boolean isCongested) {
		ServerStatus serverStatus = serverStatusRepository.findByKey(ServerStatus.SERVER_STATUS.IS_CONGESTED);
		serverStatus.setValue(isCongested);
		serverStatusRepository.save(serverStatus);
	}
}
