/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.view;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.primefaces.model.DualListModel;

/**
 *
 * @author cong0_000
 */
@ManagedBean
@RequestScoped
public class PickListView {

    @PostConstruct
    public void init(){
        List<String> citiesSource = new ArrayList<String>();
        List<String> citiesTarget = new ArrayList<String>();

        citiesSource.add("San Francisco");
        citiesSource.add("London");
        citiesSource.add("Paris");
        citiesSource.add("Istanbul");
        citiesSource.add("Berlin");
        citiesSource.add("Barcelona");
        citiesSource.add("Rome");
        citiesTarget.add("Ha Noi");

        cities = new DualListModel<String>(citiesSource, citiesTarget);
    }
    private DualListModel<String> cities = new DualListModel<String>();

    public DualListModel<String> getCities() {
//        init();
        return cities;
    }

    public void setCities(DualListModel<String> cities) {
        
        this.cities = cities;
        
    }

}
