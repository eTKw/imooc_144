package cn.nyanpasus.o2o.service;

import cn.nyanpasus.o2o.entity.HeadLine;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

public interface HeadLineService {

    List<HeadLine> getHeadLineList(HeadLine headLineCondition) throws IOException;
}
