package org.tis.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

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
import org.tis.model.Empresa;
import org.tis.model.Vaga;
 
@SuppressWarnings("serial")
@ManagedBean
public class MapaBean implements Serializable {
    
    private MapModel simpleModel;
    
    @Inject
	private VagaDao vagaDao;
    
    @PostConstruct
    public void init() {
    	
        TreeMap< String , String > map = new TreeMap< String , String >();
       
    	simpleModel = new DefaultMapModel();
    	        
        List<Vaga> vagas = vagaDao.vagasAbertasPessoa();
        
        ArrayList <Empresa> empresas = new ArrayList<Empresa>();
              
        for (Vaga e: vagas ) {
        	        	        	
        	if ( map.containsKey( e.getEmpresa().getRazaoSocial() ) ) {
        		
        		String temp = map.get(e.getEmpresa().getRazaoSocial());
        		temp = temp + " , " + e.getCargo();
        		
        		map.put(e.getEmpresa().getRazaoSocial(), temp);
        		
        	
         	}else{
         		
                map.put(e.getEmpresa().getRazaoSocial(), e.getCargo() );
                
            	empresas.add(e.getEmpresa());
         		
         	}
        	
        	
        }
        
        for (Empresa e : empresas) {
        	
        	if (map.containsKey(e.getRazaoSocial())) {
        		
        		String dados =  e.getRazaoSocial() + " : \r\n" 
   					 + "Vagas: " + map.get(e.getRazaoSocial()) +  "\r\n"
   					 + "Endereço:\r\n "
   					 + e.getLogradouro() +  " " 
   					 + e.getNumero() + "\r\n"
   					 + e.getBairro() + "\r\n"
   					 + e.getCep();
        		if (e.getLatitude().isEmpty() == true || e.getLongitude().isEmpty() == true ) {
        			
        			// NAO IMPRIME E FAZ NADA SE LONG E LAT FOREM NULAS
        			
        		}else {
        			simpleModel.addOverlay(new Marker ( new LatLng( Double.valueOf(e.getLatitude() ) , Double.valueOf( e.getLongitude() ) ) 
        									, dados ));
        		}
        	}
        }
        
    }
  
    public MapModel getSimpleModel() {
        return simpleModel;
    }
    
    @SuppressWarnings("unused")
	public void onGeocode(GeocodeEvent event) {
    	 List<GeocodeResult> results = event.getResults();
    }
    
}