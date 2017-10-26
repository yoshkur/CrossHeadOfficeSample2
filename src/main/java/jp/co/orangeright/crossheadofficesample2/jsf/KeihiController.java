package jp.co.orangeright.crossheadofficesample2.jsf;

import jp.co.orangeright.crossheadofficesample2.entity.Keihi;
import jp.co.orangeright.crossheadofficesample2.jsf.util.JsfUtil;
import jp.co.orangeright.crossheadofficesample2.jsf.util.PaginationHelper;
import jp.co.orangeright.crossheadofficesample2.ejb.KeihiFacade;

import java.io.Serializable;
import java.util.Date;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

@Named("keihiController")
@SessionScoped
public class KeihiController implements Serializable {

    private Keihi current;
    private DataModel items = null;
    @EJB
    private jp.co.orangeright.crossheadofficesample2.ejb.KeihiFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    @Inject
    private LoginController loginController;

    public KeihiController() {
    }

    public Keihi getSelected() {
        if (current == null) {
            current = new Keihi();
            selectedItemIndex = -1;
        }
        return current;
    }

    private KeihiFacade getFacade() {
        return ejbFacade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {

                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        return "List";
    }

    public String prepareView() {
        current = (Keihi) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Keihi();
        selectedItemIndex = -1;
        return "Create";
    }

    public String prepareCreateItemKeihi(Integer itemid) {
        this.current = new Keihi();
        this.current.setItemid(itemid);
        this.current.setKonetsuhi(0);
        this.current.setKosaihi(0);
        this.current.setSharyo(0);
        this.current.setShomohin(0);
        this.current.setShuzenhi(0);
        this.current.setZappi(0);
        selectedItemIndex = -1;
        return "/keihi/CreateItemKeihi?faces-redirect=true";
    }

    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("KeihiCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String createItemKeihi() {
        try {
            this.current.setRecorddate(new Date());
            this.current.setRecordprogram(this.getClass().getName());
            this.current.setRecorduserid(this.loginController.getLoginUser().getUserid());
            this.current.setRecordvalid(Boolean.TRUE);
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("KeihiCreated"));
            return "/item/View?faces-redirect=true";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (Keihi) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String prepareEditItemKeihi() {
        return "/keihi/EditItemKeihi?faces-redirect=true";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("KeihiUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String updateItemKeihi() {
        try {
            this.current.setRecorddate(new Date());
            this.current.setRecordprogram(this.getClass().getName());
            this.current.setRecorduserid(this.loginController.getLoginUser().getUserid());
            this.current.setRecordvalid(Boolean.TRUE);
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("KeihiUpdated"));
            return "/item/View?faces-redirect=true";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (Keihi) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("KeihiDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    public Keihi getKeihi(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    public void setCurrent(java.lang.Integer id) {
        this.current = this.ejbFacade.find(id);
    }

    public boolean isKeihi() {
        return this.current != null;
    }

    @FacesConverter(forClass = Keihi.class)
    public static class KeihiControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            KeihiController controller = (KeihiController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "keihiController");
            return controller.getKeihi(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Keihi) {
                Keihi o = (Keihi) object;
                return getStringKey(o.getItemid());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Keihi.class.getName());
            }
        }

    }

}
