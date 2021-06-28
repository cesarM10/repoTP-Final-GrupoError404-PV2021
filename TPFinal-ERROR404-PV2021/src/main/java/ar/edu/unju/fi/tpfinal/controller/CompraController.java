package ar.edu.unju.fi.tpfinal.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.tpfinal.model.Order;
import ar.edu.unju.fi.tpfinal.model.OrderDetail;
import ar.edu.unju.fi.tpfinal.model.OrderDetailsID;
import ar.edu.unju.fi.tpfinal.model.Payment;
import ar.edu.unju.fi.tpfinal.model.PaymentsID;
import ar.edu.unju.fi.tpfinal.service.ICustomerService;
import ar.edu.unju.fi.tpfinal.service.IOrderDetailService;
import ar.edu.unju.fi.tpfinal.service.IOrderService;
import ar.edu.unju.fi.tpfinal.service.IPaymentService;
import ar.edu.unju.fi.tpfinal.service.IProductService;
import ar.edu.unju.fi.tpfinal.service.imp.ProductServiceMysql;

@Controller
public class CompraController {
	private static final Log LOGGER = LogFactory.getLog(ProductServiceMysql.class);
	
	@Autowired
	@Qualifier("orderDetailServiceMysql")
	private IOrderDetailService orderDetailService; //inyeccion OrderDetailService con metodos que trabaja con la base de datos.
	
	@Autowired
	@Qualifier("orderServiceMysql")
	private IOrderService orderService; //inyeccion OrdereService con metodos que trabaja con la base de datos.
	
	@Autowired
	@Qualifier("productServiceMysql")
	private IProductService productService; //inyeccion ProductService con metodos que trabaja con la base de datos.
	
	@Autowired
	@Qualifier("customerServiceMysql")
	private ICustomerService customerService; //inyeccion CustomerService con metodos que trabaja con la base de datos.
	
	@Autowired
	@Qualifier("paymentServiceMysql")
	private IPaymentService paymentService; //inyeccion PaymentService con metodos que trabaja con la base de datos.
	
	
	/**
	 * Metodo que envia un objeto order para realizar el alta de una nueva order.
	 * customer para realizar la busqueda de un cliente.
	 * customers para cargar el producto encontrado resultante de la busqueda.
	 * @param model
	 * @return Dirige a alta_order.
	 */
	@GetMapping("/compra/nueva/orden")
	public String getNuevaCompraPage(Model model) {
		
		model.addAttribute("order", orderService.getOrder());
		model.addAttribute("customer", customerService.getCustomer());
		model.addAttribute("customers", customerService.listaCustomerSeleccionado());
		
		return "alta_order";
	}
	
	/**
	 * Metodo que realiza la busqueda de un cliente mediante el parametro customerNumber.
	 * @param customerNumber
	 * @param model
	 * @return Dirige a alta_order con el cliente cargado.
	 */
	@GetMapping("/compra/busqueda/customer")
	public String getBuscarCustomerConFiltro(@RequestParam(value = "customerNumber")Long customerNumber, Model model) {
		LOGGER.info("METODO - - BUSCAR");
		model.addAttribute("order", orderService.getOrder());
		model.addAttribute("customer", customerService.getCustomer());		
		model.addAttribute("customerSeleccionado", customerService.buscarCustomerPorCustomerNumber(customerNumber));
		LOGGER.info("METODO - - BUSCAR - - PASO");
		
		return "alta_order";
	}
	
	/**
	 * Metodo que guarda una order solo si el resultadoValidacion no contiene ningun error.
	 * Settea algunos valores automaticos de order y el cliente seleccionado. 
	 * @param order
	 * @param resultadoValidacion
	 * @return En caso de error del resultadoValidacion, dirige a alta_order sino
	 * dirige a alta_compra.
	 */
	@PostMapping("/compra/guardar/order")
	public ModelAndView agregarCompraPage(@Valid @ModelAttribute("order")Order order, BindingResult resultadoValidacion) {
		//ModelAndView model = new ModelAndView("alta_compra");
		ModelAndView model;
		
		if(resultadoValidacion.hasErrors()){
			model = new ModelAndView("alta_order");
			model.addObject("customer", customerService.getCustomer());		
			model.addObject("customerSeleccionado", customerService.listaCustomerSeleccionado());
			
			return model;
		}else {
			model = new ModelAndView("alta_compra");
			
			order.setCustomer(customerService.listaCustomerSeleccionado().get(0));
			order.setOrderDate(LocalDate.now());
			order.setRequiredDate(LocalDate.now());
			
			orderService.agregarOrder(order);
			
			model.addObject("product", productService.getProduct());
			model.addObject("productosSeleccionados", productService.listaDeProductos());
			model.addObject("orderDetail", orderDetailService.getOrderDetail());
			    
			return model;
		}
		
		
	}
	
	/**
	 * Metodo que guarda un OrderDetail.
	 * Instancia un objeto OrderDetailID que funciona como una primaryKey combinada de Product
	 * y Order y se settea como id a OrderDetail.
	 * Se settea priceEach del producto seleccionado en orderDetail.
	 * Guarda el OrderDetail en la base de datos y retorna al mismo formulario con 
	 * mensaje de confirmacion de operacion.
	 * 
	 * @param orderDetail
	 * @param resultadoValidacion
	 * @return
	 */
	@PostMapping("/compra/guardar")
	public ModelAndView agregarProductoAOrderPage(@Valid @ModelAttribute("orderDetail")OrderDetail orderDetail, BindingResult resultadoValidacion) {
		//ModelAndView model = new ModelAndView("alta_compra");
		ModelAndView model;
		
		if(resultadoValidacion.hasErrors()) {
			model = new ModelAndView("alta_compra");
			
			model.addObject("product", productService.getProduct());
			model.addObject("productosSeleccionados", productService.listaDeProductos());
		
			return model;
		}else {
			model = new ModelAndView("alta_compra");
			
			LOGGER.info("entro a añadir");
			OrderDetailsID id = new OrderDetailsID(orderService.obtenerOrder().get(orderService.obtenerOrder().size()-1), productService.listaDeProductos().get(0));
			LOGGER.info("paso la asignacion del ID");
			orderDetail.setId(id);
			orderDetail.setPriceEach(productService.listaDeProductos().get(0).getMSRP());
			
			int i = Math.toIntExact(orderDetail.getId().getOrderNumber().getOrderNumber());
			orderDetail.setOrderLineNumber(i);
			
			orderDetailService.agregarOrderDetail(orderDetail);
			
			model.addObject("product", productService.getProduct());
			model.addObject("productosSeleccionados", productService.listaDeProductos());
			model.addObject("orderDetail", orderDetailService.getOrderDetail());
			model.addObject("mensaje", "Se añadio correctamente al carrito");
			
			return model;
		}
		
		
	}
	
	/**
	 * Metodo que permite la busqueda de un producto mediante productCode.
	 * @param productCode
	 * @param model
	 * @return Dirige a alta_compra.
	 */
	@GetMapping("/compra/busqueda")
	public String getBuscarProductoConFiltro(@RequestParam(value = "productCode")Long productCode, Model model) {
		LOGGER.info("METODO - - BUSCAR");
		model.addAttribute("product", productService.getProduct()); 
		model.addAttribute("orderDetail", orderDetailService.getOrderDetail());		
		model.addAttribute("productosSeleccionados", productService.buscarProductPorProductCode(productCode));
		LOGGER.info("METODO - - BUSCAR - - PASO");
		
		return "alta_compra";
	}
	
	/**
	 * Metodo que recupera en una lista los productos pertenecientes a la ultima OrderDetail
	 * Se calcula el subtotal de la compra por cada producto y el monto total de la misma.
	 * @param model
	 * @return Dirige a carrito_compra.
	 */
	@GetMapping("/compra/carrito")
	public String mostrarCarritoDeCompra(Model model) {
		List<OrderDetail> listaEnCarrito = orderDetailService.buscarProductosPorOrderNumber(orderDetailService.obtenerOrderDetails().get(orderDetailService.obtenerOrderDetails().size()-1).getOrderLineNumber());
		double totalAPagar = 0;
		for (OrderDetail orderDetail : listaEnCarrito) {
			totalAPagar = orderDetail.getSubTotal() + totalAPagar;
		}
		
		PaymentsID id = new PaymentsID(listaEnCarrito.get(0).getId().getOrderNumber().getCustomer(), "AA");
		Payment payment = new Payment(id, LocalDate.now(), totalAPagar);
		paymentService.agregarPayment(payment);
		
		model.addAttribute("orderDetail", listaEnCarrito);
		model.addAttribute("total", totalAPagar);
		
		return "carrito_compra";
	}
}
