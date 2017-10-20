package org.tis.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import org.primefaces.event.map.GeocodeEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.GeocodeResult;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import org.tis.dao.VagaDao;
import org.tis.model.Vaga;
 
@ManagedBean
public class MapaBean implements Serializable {
    
    private MapModel simpleModel;
    
    @Inject
	private VagaDao vagaDao;
    
    @PostConstruct
    public void init() {
       
    	simpleModel = new DefaultMapModel();
    	
        ArrayList <LatLng> coordenadas = new ArrayList<LatLng>();
        
        List<Vaga> vagas = vagaDao.vagasAbertasPessoa();
        
        for ( Vaga e : vagas ) {
        	
        		simpleModel.addOverlay(new Marker ( new LatLng( Double.valueOf(e.getEmpresa().getLatitude() ) , Double.valueOf( e.getEmpresa().getLongitude() ) ) , e.getCargo() ) );
        		
        }

    }
  
    public MapModel getSimpleModel() {
        return simpleModel;
    }
    
    public void onGeocode(GeocodeEvent event) {
    	 List<GeocodeResult> results = event.getResults();
    }
    
    public ArrayList montarVagas(){
    	List<Vaga> vagas = vagaDao.vagasAbertasPessoa();
    	String endereco;
    	for( Vaga e : vagas ){
    		endereco = null;
    		endereco = e.getEmpresa().getLogradouro() + " ";
    		endereco+= e.getEmpresa().getNumero() + " ";
    		endereco+= e.getEmpresa().getBairro() + " ";
    		endereco+= e.getEmpresa().getMunicipio() + " ";
    	}
    	
    	return (ArrayList) vagas;
    }
}