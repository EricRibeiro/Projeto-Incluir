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
    	        
        List<Vaga> vagas = vagaDao.vagasAbertasPessoa();
        
        ArrayList <String> nomes = new ArrayList<String>();
        
        int i = 0;
        
        for (Vaga e: vagas ) {
        	
        	nomes.add(e.getEmpresa().getRazaoSocial());
        	
        }
        
        for ( Vaga e : vagas ) {
        	
        	//TENTATIVAS DE USAR FUNCAO LAMBDA PARA FILTAR E ENCONTRAR O VALOR DE STRING IGUAL DIRETO NA COLLECTION
    		//vagas.stream().anyMatch( String -> e.getEmpresa().getRazaoSocial().equalsIgnoreCase(s)
    		//nomes.stream().filter( str -> str.trim().equals(s+"") ) != null 
    		//nomes.toString().contains(e.getEmpresa().getRazaoSocial())
        	// 
        	//IDEIA NOVA : USAR UM MAPA PARA MAPEAR UMA STRING (nome da empresa ) para uma collection de marcadores e iterar neles.
        	//
        	// MAIS FACIL IMPLEMENTADA COM UM ITERATOR: 
        	
        	i = 0;
        	
        	for (String n : nomes ) {
        		
        		if (n.equalsIgnoreCase(e.getEmpresa().getRazaoSocial())) {
        			
        			i++;
        			
        		}
        		
        	}
        	// nao trata vaga em duplicidade. precisa-se mapear.
        	if ( true ){
        		 
        		String dados =  e.getEmpresa().getRazaoSocial() + " : \r\n" 
 							 + "Vaga: " + e.getCargo() +  "\r\n"
 							 + "Endereço:\r\n"
 							 + e.getEmpresa().getLogradouro() +  " " 
 							 + e.getEmpresa().getNumero() + "\r\n"
 							 + e.getEmpresa().getBairro() + "\r\n"
 							 + e.getEmpresa().getCep();
        		
        		simpleModel.addOverlay(new Marker ( new LatLng( Double.valueOf(e.getEmpresa().getLatitude() ) , Double.valueOf( e.getEmpresa().getLongitude() ) ) 
       				   , dados ));
         		 	
        	}
        	else {
        	
        	
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
    
    public ArrayList<Vaga> montarVagas(){
    	List<Vaga> vagas = vagaDao.vagasAbertasPessoa();
    	String endereco;
    	return (ArrayList) vagas;
    }
}