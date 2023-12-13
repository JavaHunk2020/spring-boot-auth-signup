package com.technohunk.service;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
	
	public List<SignupDTO> findSignups() {
		return signupRepository.findAll().stream().map(s->{
			SignupDTO signupDTO=new SignupDTO();
			signupDTO.setName(s.getName());
			signupDTO.setEmail(s.getEmail());
			signupDTO.setNisha(s.getNisha());
			signupDTO.setDoe(s.getDoe());
			signupDTO.setRole(s.getRole());
			return signupDTO;
		}).collect(Collectors.toList());
	}
	
	public void saveSigup(SignupDTO signupDTO) {
		Signup signup=new Signup();
		BeanUtils.copyProperties(signupDTO, signup);
		signup.setPassword(passwordEncoder.encode(signup.getPassword()));
		signupRepository.save(signup);
	}
	
	public boolean isAlreadySignup(String email,String name) {
		return signupRepository.findByEmailAndName(email, name).isPresent();
	}
	
	public void deleteSignupByEmail(String email) {
		signupRepository.findByEmail(email).forEach(s->signupRepository.delete(s));
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
		Signup signup=signupRepository.findByEmail(email).get(0);
		historyEntity.setSignup(signup);
		
		LoginHistoryEntity dbEntity=loginHistoryRepository.save(historyEntity);
		LoginHistoryDTO historyDTO=new LoginHistoryDTO();
		BeanUtils.copyProperties(dbEntity, historyDTO);
		return historyDTO;
	}
	
	public boolean findByEmailAndPassword(String email,String password) {
		boolean status=false;
		List<Signup> list=signupRepository.findByEmail(email);
		if(list.size()>0) {
			status = passwordEncoder.matches(password, list.get(0).getPassword());
		}
		return status;
	}
	
	public boolean existsByEmail(String email) {
		List<Signup> list=signupRepository.findByEmail(email);
		return list.size()> 0;
	}
}
