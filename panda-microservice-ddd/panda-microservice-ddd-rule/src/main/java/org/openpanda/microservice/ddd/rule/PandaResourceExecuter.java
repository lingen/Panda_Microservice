package org.openpanda.microservice.ddd.rule;

import javax.ws.rs.core.Response;

/**
 * Created by lingen on 2016/12/24.
 */
public class PandaResourceExecuter {

    public static String Panda_Error = "Serve Error";

    public static String SUCCESS = "Success";

    public static String Fail = "Fail";

    public static Response execute(PandaResource pandaResource){

        try{
            Response response = pandaResource.execute();
            return response;
        }catch (PandaRuntimeException e){
            return Response.status(400).entity(PandaRestResponse.createInstance(e.getErrorCode(),e.getDescription())).build();
        }catch(Exception e){
            return Response.status(400).entity(PandaRestResponse.createInstance(Panda_Error,"")).build();
        }
    }

    public static Response executeWithReturnBool(PandaResourceWithReturnBool pandaResourceWithReturnBool){
        try{
            boolean success = pandaResourceWithReturnBool.execute();
            if (success){
                return Response.status(200).entity(PandaRestResponse.createInstance(SUCCESS,"")).build();
            }else{
                return Response.status(400).entity(PandaRestResponse.createInstance(Fail,"")).build();
            }
        }catch (PandaRuntimeException e){
            return Response.status(400).entity(PandaRestResponse.createInstance(e.getErrorCode(),e.getDescription())).build();
        }catch(Exception e){
            return Response.status(400).entity(PandaRestResponse.createInstance(Panda_Error,"")).build();
        }
    }


}
