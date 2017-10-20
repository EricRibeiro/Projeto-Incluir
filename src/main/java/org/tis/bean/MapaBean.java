package org.tis.bean;

import java.io.Serializable;
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
          
        //Shared coordinates
        LatLng coord1 = new LatLng(36.879466, 30.667648);
        LatLng coord2 = new LatLng(36.883707, 30.689216);
        LatLng coord3 = new LatLng(36.879703, 30.706707);
        LatLng coord4 = new LatLng(36.885233, 30.702323);
          
        //Basic marker
        simpleModel.addOverlay(new Marker(coord1, "Konyaalti"));
        simpleModel.addOverlay(new Marker(coord2, "Ataturk Parki"));
        simpleModel.addOverlay(new Marker(coord3, "Karaalioglu Parki"));
        simpleModel.addOverlay(new Marker(coord4, "Kaleici"));
    }
  
    public MapModel getSimpleModel() {
        return simpleModel;
    }
    
    public void onGeocode(GeocodeEvent event) {
    	 List<GeocodeResult> results = event.getResults();
    }
    
    public void montarVagas(){
    	List<Vaga> vagas = vagaDao.vagasAbertasPessoa();
    	String endereco;
    	for( Vaga e : vagas ){
    		endereco = null;
    		endereco = e.getEmpresa().getLogradouro() + " ";
    		endereco+= e.getEmpresa().getNumero() + " ";
    		endereco+= e.getEmpresa().getBairro() + " ";
    		endereco+= e.getEmpresa().getMunicipio() + " ";
    	}
    }
}