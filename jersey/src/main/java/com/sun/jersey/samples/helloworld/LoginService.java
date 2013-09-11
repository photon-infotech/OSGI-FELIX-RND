/**
 * Framework Web Archive
 *
 * Copyright (C) 1999-2013 Photon Infotech Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.sun.jersey.samples.helloworld;


import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.photon.phresco.framework.rest.model.Credentials;
import com.photon.phresco.framework.rest.model.ResponseInfo;


/**
 * The Class LoginService.
 */
@Path("/login")
public class LoginService {
	
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response authenticate(Credentials credentials) {
		ResponseInfo<String> responseData = new ResponseInfo<String>();
		try {
			if(credentials.getUsername().equalsIgnoreCase(credentials.getPassword())){
				ResponseInfo<String> finalOuptut = responseDataEvaluation(responseData, null, "authenticated successfully", "success", "successcode");
				return Response.ok(finalOuptut).header("Access-Control-Allow-Origin", "*").build();	
			}else{
				ResponseInfo<String> finalOuptut = responseDataEvaluation(responseData, null, "pass the username and password same", "failure", "failurecode");
				return Response.ok(finalOuptut).header("Access-Control-Allow-Origin", "*").build();
			}
			
		} 
			catch (Exception e) {
			return Response.status(Status.OK).entity("").header(
					"Access-Control-Allow-Origin", "*").build();
		}
	}
	
	protected <T> ResponseInfo<T> responseDataEvaluation(ResponseInfo<T> responseData, Exception e, T data, String status, String responseCode ) {
		responseData.setException(e);
		responseData.setData(data);
		responseData.setStatus(status);
		responseData.setResponseCode(responseCode);
		
		return responseData;
	}

	
}
