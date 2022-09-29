package ar.com.educacionit.web.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.com.educacionit.domain.Menu;
import ar.com.educacionit.services.MenuService;
import ar.com.educacionit.services.exceptions.ServiceException;
import ar.com.educacionit.services.impl.MenuServiceImpl;
import ar.com.educacionit.web.enums.AttributesEnum;
import ar.com.educacionit.web.enums.ViewsEnum;


@WebServlet("/controllers/menu")
public class MenuController extends BaseServlet {

		private static final long serialVersionUID = -8233638500628006680L;

		public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
				
			ViewsEnum target = ViewsEnum.MENU; 								// lo uso como vista por defecto
			
			MenuService ms = new MenuServiceImpl();
			List<Menu> menu;
			List<Menu> menuOrdenado;
			
			
			try {
				menu = ms.findAll();
				menuOrdenado = buildMenu(menu);								// guardo el menu ordenado con sus submenu
				setAttibute(AttributesEnum.MENU, request, menuOrdenado);
	
			} catch (ServiceException e) {
				target = ViewsEnum.REGISTRO_FAIL; 							// si falla cambio el target
			}
			
			redirect(target, request, response); 								
		}
		
	
		
		private static List<Menu> buildMenu(List<Menu> listMenu){
			
			Map<Long, Menu> mapRoot = new HashMap();
			List<Menu> menuFiltrado = new ArrayList();
			
			for(Menu m: listMenu) {
				if(m.isRoot()) {
					mapRoot.put(m.getId(), m);
				}
			}
			
			for(Menu m : listMenu) {
				if(!m.isRoot()) {
					if(m.getIdMenuPadre()!=null && m.getId() !=0){
						Menu menuRoot = mapRoot.get(m.getIdMenuPadre());
						if(menuRoot != null) {
							menuRoot.getSubMenu().add(m);
						} else {
							addToParent(listMenu, m);
						}
					}
				}
			}
			
			List<Menu> menuOrdenado = new ArrayList<Menu>();
			menuOrdenado.addAll(mapRoot.values());
			return menuOrdenado;
			
		}
		
		private static void addToParent(List<Menu> listMenu, Menu m) {
			boolean exists = false;
			for(int i=0; !exists && i<listMenu.size(); i++) {
				Menu aux = listMenu.get(i);
				if(m.getIdMenuPadre().equals(aux.getId())){
					aux.getSubMenu().add(m);
					exists = true;
				}
			}
		}
		
		
}