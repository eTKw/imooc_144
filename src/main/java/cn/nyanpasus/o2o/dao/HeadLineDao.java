package cn.nyanpasus.o2o.dao;

import cn.nyanpasus.o2o.entity.HeadLine;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HeadLineDao {
    List<HeadLine> queryHeadLine(@Param("headLineCondition") HeadLine headLineCondition);
}
