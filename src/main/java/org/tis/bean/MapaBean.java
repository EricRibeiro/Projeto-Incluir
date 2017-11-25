package org.tis.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import org.primefaces.event.map.GeocodeEvent;
import org.primefaces.event.map.OverlaySelectEvent;
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
@ViewScoped
public class MapaBean implements Serializable {

	private MapModel advancedModel;
	private Marker marker;
	private MarkerEx markerEx;
	private TreeMap<String, String[]> mapMarker = new TreeMap<String, String[]>();

	@Inject
	private VagaDao vagaDao;

	@PostConstruct
	public void init() {

		TreeMap<String, String> map = new TreeMap<String, String>();

		advancedModel = new DefaultMapModel();

		List<Vaga> vagas = vagaDao.vagasAbertasPessoa();

		ArrayList<Empresa> empresas = new ArrayList<Empresa>();

		for (Vaga e : vagas) {

			if (map.containsKey(e.getEmpresa().getRazaoSocial())) {

				String temp = map.get(e.getEmpresa().getRazaoSocial());
				temp = temp + " , " + e.getCargo();

				map.put(e.getEmpresa().getRazaoSocial(), temp);

			} else {

				map.put(e.getEmpresa().getRazaoSocial(), e.getCargo());

				empresas.add(e.getEmpresa());

			}

		}

		for (Empresa e : empresas) {

			if (map.containsKey(e.getRazaoSocial())) {

				String dados = e.getRazaoSocial() + " : \r\n" + "Vagas: " + map.get(e.getRazaoSocial()) + "\r\n"
						+ "Endereço:\r\n " + e.getLogradouro() + " " + e.getNumero() + "\r\n" + e.getBairro() + "\r\n"
						+ e.getCep();
				if (e.getLatitude().isEmpty() == true || e.getLongitude().isEmpty() == true) {

					// NAO IMPRIME E FAZ NADA SE LONG E LAT FOREM NULAS

				} else {
					Marker mk = new Marker(
							new LatLng(Double.valueOf(e.getLatitude()), Double.valueOf(e.getLongitude())), dados);
					advancedModel.addOverlay(mk);
					String[] temp = new String[6];

					temp[0] = e.getRazaoSocial();
					temp[1] = map.get(e.getRazaoSocial());
					temp[2] = e.getLogradouro();
					temp[3] = e.getNumero();
					temp[4] = e.getBairro();
					temp[5] = e.getCep();
					mapMarker.put(mk.getTitle(), temp);
				}

			}
		}

	}

	public MapModel getAdvancedModel() {
		return advancedModel;
	}

	@SuppressWarnings("unused")
	public void onGeocode(GeocodeEvent event) {
		List<GeocodeResult> results = event.getResults();
	}

	public void onMarkerSelect(OverlaySelectEvent event) {
		marker = (Marker) event.getOverlay();

		String[] temp = mapMarker.get(marker.getTitle());
		markerEx = new MarkerEx(marker.getLatlng(), marker.getTitle());
		markerEx.setRazaoSocial(temp[0]);
		markerEx.setVagas(temp[1]);
		markerEx.setLogradouro(temp[2]);
		markerEx.setNumero(temp[3]);
		markerEx.setBairro(temp[4]);
		markerEx.setCep(temp[5]);
	}

	public Marker getMarker() {
		return marker;
	}

	public MarkerEx getMarkerEx() {
		return markerEx;
	}

}