package com.example.sharding_db.service;

import com.example.sharding_db.config.ShardRoutingDatasource;
import com.example.sharding_db.entity.Users;
import com.example.sharding_db.repository.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UsersServiceImpl {

	private final UsersRepository usersRepository;

	public UsersServiceImpl(UsersRepository usersRepository){
		this.usersRepository = usersRepository;
	}

	public Users findUser(Long id){
		if(id < 19){
			log.info("Masok");
			ShardRoutingDatasource.setCurrentShard("shard1");
		}else {
			ShardRoutingDatasource.setCurrentShard("shard2");
		}



		Users data = usersRepository.findById(id).orElse(null);
		log.info("Data : {} " , data);
		ShardRoutingDatasource.clear();

		return data;
	}
}
