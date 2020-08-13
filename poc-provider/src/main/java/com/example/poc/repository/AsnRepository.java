package com.example.poc.repository;

import com.example.poc.dto.AsnDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectKey;

@Mapper
public interface AsnRepository {

  @Insert("INSERT INTO tbl_ESB_RCPT(hjs_node_id, hjs_sequence, hjs_error_number) " +
      "VALUES (#{trailerNumber}, 1, -1)")
  @SelectKey(
      keyProperty = "trailerNumber",
      resultType = String.class,
      before = true,
      statement = "select newId()")
  Integer insertAsn(AsnDto asnDto);

}
