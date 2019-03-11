package br.com.wallace.classificacao.domain.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Builder
@Document(value = "logs")
@NoArgsConstructor
@AllArgsConstructor
public class LogModel {
    @Id
    private ObjectId _id;
    @CreatedDate
    private Date date;
    @LastModifiedDate
    private Date dateResponse;
    private int responseCode;
    private String endPoint;
    private String method;
    private String user;
    private String ip;
}
