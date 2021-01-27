package cn.edu.sdust.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.sdust.domain.Tutor;
import cn.edu.sdust.mapper.ITutorMapper;
import cn.edu.sdust.service.ITutorService;


@Service("ITutorService")
public class TutorServiceImpl implements ITutorService{
	@Autowired
	private ITutorMapper tutormapper;

	@Override
	public List<Tutor> getAllTutors() {
		// TODO Auto-generated method stub
		return tutormapper.selectAllTutors();
	}

	@Override
	public int addTutor(Tutor tutor) {
		// TODO Auto-generated method stub
		return tutormapper.insertTutor(tutor);
	}

}
