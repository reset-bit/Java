package cn.edu.sdust.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.edu.sdust.domain.Tutor;

public interface ITutorMapper {
	public List<Tutor> selectAllTutors();
	public int insertTutor(Tutor tutor);
}
