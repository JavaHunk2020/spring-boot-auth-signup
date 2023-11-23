package com.technohunk.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.technohunk.dto.LoginHistoryDTO;
import com.technohunk.dto.SignupDTO;
import com.technohunk.entity.LoginHistoryEntity;
import com.technohunk.entity.Signup;
import com.technohunk.repository.LoginHistoryRepository;
import com.technohunk.repository.SignupRepository;

@Service
public class SignupService {
	
	@Autowired
	private SignupRepository signupRepository;
	
	@Autowired
	private LoginHistoryRepository loginHistoryRepository;
	
	/**
	 * Who will call this methid ???? spring security
	 */
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public void saveSigup(SignupDTO signupDTO) {
		Signup signup=new Signup();
		BeanUtils.copyProperties(signupDTO, signup);
		signup.setPassword(passwordEncoder.encode(signup.getPassword()));
		signupRepository.save(signup);
	}
	
	public void deleteLoginHistoryById(int  dbid) {
		loginHistoryRepository.deleteById(dbid);
	}
	
	public List<LoginHistoryDTO> findByEmail(String  username) {
		List<LoginHistoryEntity> loginHistoryList=loginHistoryRepository.findByEmail(username);
		List<LoginHistoryDTO> historyDTOs=new ArrayList<LoginHistoryDTO>();
		for(LoginHistoryEntity  entity:loginHistoryList) {
			LoginHistoryDTO historyDTO=new LoginHistoryDTO();
			BeanUtils.copyProperties(entity, historyDTO);
			Signup signup=entity.getSignup();
			SignupDTO signupDTO=new SignupDTO();
			BeanUtils.copyProperties(signup, signupDTO);
			historyDTO.setSignup(signupDTO);
			historyDTOs.add(historyDTO);
		}
		return historyDTOs;
	}
	
	public LoginHistoryDTO findLoginHistoryByDbId(int dbid) {
		LoginHistoryEntity historyEntity=loginHistoryRepository.findById(dbid).get();
		LoginHistoryDTO historyDTO=new LoginHistoryDTO();
		BeanUtils.copyProperties(historyEntity, historyDTO);
		return historyDTO;
	}
	
	@Transactional
	public void saveHistoryEntity(LoginHistoryDTO loginHistoryDTO) { //Lhid, logout_time , duration
		//Loading entity here inside persistence context
		LoginHistoryEntity loginHistoryEntity=loginHistoryRepository.findById(loginHistoryDTO.getLhid()).get();
		//Changing the state of the entity
		//Automatic Dirty checking mechanism
		loginHistoryEntity.setDuration(loginHistoryDTO.getDuration());
		loginHistoryEntity.setLogout_time(loginHistoryDTO.getLogout_time());
		//loginHistoryRepository.save(loginHistoryEntity);
	}
	
	public LoginHistoryDTO createHistoryEntity(LoginHistoryDTO loginHistoryDTO,String email) {
		
		LoginHistoryEntity historyEntity=new LoginHistoryEntity();
		BeanUtils.copyProperties(loginHistoryDTO, historyEntity);
		Signup signup=signupRepository.findByEmail(email).get();
		historyEntity.setSignup(signup);
		
		LoginHistoryEntity dbEntity=loginHistoryRepository.save(historyEntity);
		LoginHistoryDTO historyDTO=new LoginHistoryDTO();
		BeanUtils.copyProperties(dbEntity, historyDTO);
		return historyDTO;
	}
	
	public boolean findByEmailAndPassword(String email,String password) {
		boolean status=false;
		Optional<Signup> optional=signupRepository.findByEmail(email);
		if(optional.isPresent()) {
			status = passwordEncoder.matches(password, optional.get().getPassword());
		}
		return status;
	}
	

}
