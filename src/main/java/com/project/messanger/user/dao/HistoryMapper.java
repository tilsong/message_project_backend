package com.project.messanger.user.dao;

import com.project.messanger.user.model.Promise;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HistoryMapper {

    List<Promise> getPromiseHistory();
}
