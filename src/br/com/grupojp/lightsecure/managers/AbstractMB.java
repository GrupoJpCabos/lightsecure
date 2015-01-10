package br.com.grupojp.lightsecure.managers;
 
import org.primefaces.context.RequestContext;

public class AbstractMB {
    private static final String KEEP_DIALOG_OPENED = "KEEP_DIALOG_OPENED";
 
    public AbstractMB() {
        super();
    }
 
    protected void menssagemErro(String message) {
        JSFMessageUtil messageUtil = new JSFMessageUtil();
        messageUtil.sendError(message);
    }
 
    protected void menssagemInfo(String message) {
        JSFMessageUtil messageUtil = new JSFMessageUtil();
        messageUtil.sendInfo(message);
    }
    protected void menssagemGrave(String message){
    	JSFMessageUtil messageUtil = new JSFMessageUtil();
    	messageUtil.deleteAdmin(message);
    }
 
    protected void closeDialog(){
        getRequestContext().addCallbackParam(KEEP_DIALOG_OPENED, false);
    }
 
    protected void keepDialogOpen(){
        getRequestContext().addCallbackParam(KEEP_DIALOG_OPENED, true);
    }
 
    protected RequestContext getRequestContext(){
        return RequestContext.getCurrentInstance();
    }
}