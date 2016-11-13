package jp.co.orangeright.crossheadofficesample2.jsf;

import jp.co.orangeright.crossheadofficesample2.entity.UserM;
import jp.co.orangeright.crossheadofficesample2.jsf.util.JsfUtil;
import jp.co.orangeright.crossheadofficesample2.jsf.util.PaginationHelper;
import jp.co.orangeright.crossheadofficesample2.ejb.UserMFacade;

import java.io.Serializable;
import java.util.List;
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
import jp.co.orangeright.crossheadofficesample2.ejb.GroupMFacade;
import jp.co.orangeright.crossheadofficesample2.entity.GroupM_;
import jp.co.orangeright.crossheadofficesample2.entity.UserM_;
import jp.co.orangeright.crossheadofficesample2.jsf.groupm.GroupMSearchCondition;
import jp.co.orangeright.crossheadofficesample2.jsf.userm.UserMSearchCondition;

@Named("userMController")
@SessionScoped
public class UserMController implements Serializable {

    private UserM current;
    private List<UserM> userList;
    private DataModel items = null;
    @EJB
    private jp.co.orangeright.crossheadofficesample2.ejb.UserMFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    private UserMSearchCondition condition;
    @EJB
    private GroupMFacade groupFacade;
    @Inject
    private ItemController iCon;

    public UserMController() {
    }

    public List<UserM> getUserList() {
        if (this.userList == null) {
            this.userList = this.ejbFacade.findAll();
        }
        return userList;
    }

    public void setUserList(List<UserM> userList) {
        this.userList = userList;
    }

    public UserM getSelected() {
        if (current == null) {
            current = new UserM();
            selectedItemIndex = -1;
        }
        return current;
    }

    private UserMFacade getFacade() {
        return ejbFacade;
    }

    public UserMSearchCondition getCondition() {
        if (this.condition == null) {
            this.condition = new UserMSearchCondition();
            GroupMSearchCondition gCon = new GroupMSearchCondition();
            gCon.setAsc(Boolean.TRUE);
            gCon.setOrderBy(GroupM_.roworder);
            this.condition.setGroupList(this.groupFacade.findAll(gCon));
            this.condition.setAsc(Boolean.TRUE);
            this.condition.setOrderBy(UserM_.roworder);
        }
        return condition;
    }

    public void setCondition(UserMSearchCondition condition) {
        this.condition = condition;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(20) {

                @Override
                public int getItemsCount() {
                    return getFacade().count(getCondition());
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}, getCondition()));
                }
            };
        }
        return pagination;
    }

    public String selectUser() {
        current = (UserM) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        this.iCon.getCondition().setUser(current);
        return this.iCon.getUserSearchReturnPage();
    }

    public String clearUser() {
        this.iCon.getCondition().setUser(null);
        return this.iCon.getUserSearchReturnPage();
    }

    public String clearCondition() {
        this.getCondition().setGroup(null);
        this.getCondition().setUserName(null);
        return "/userM/Search?faces-redirect=true";
    }

    public String prepareSearch() {
        return "Search";
    }

    public String searchList() {
        this.getPagination().setPage(0);
        recreateModel();
        return "List?faces-redirect=true";
    }

    public String prepareList() {
        recreateModel();
        return "List";
    }

    public String prepareView() {
        current = (UserM) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new UserM();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("UserMCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (UserM) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("UserMUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (UserM) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("UserMDeleted"));
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
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}, this.getCondition()).get(0);
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
        return JsfUtil.getSelectItems(ejbFacade.findAll(this.getCondition()), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(this.getCondition()), true);
    }

    public UserM getUserM(java.lang.String id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = UserM.class)
    public static class UserMControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            UserMController controller = (UserMController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "userMController");
            return controller.getUserM(getKey(value));
        }

        java.lang.String getKey(String value) {
            java.lang.String key;
            key = value;
            return key;
        }

        String getStringKey(java.lang.String value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof UserM) {
                UserM o = (UserM) object;
                return getStringKey(o.getUserid());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + UserM.class.getName());
            }
        }

    }

}
