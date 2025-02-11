package com.zurich.policy_client.api.response;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ResponseBean implements Serializable {
  /** Serial Version UID */
  private static final long serialVersionUID = 1L;
  /** Response success flag */
  private boolean success;
  /** Response code */
  private Integer code;
  /** Response Payload */
  private transient Object payload;
  /** Response message */
  private String messages;
 
}
