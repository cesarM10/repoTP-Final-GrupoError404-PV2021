package ar.edu.unju.fi.tpfinal.controller;

import java.time.LocalDate;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	private IOrderDetailService orderDetailService;
	
	@Autowired
	@Qualifier("orderServiceMysql")
	private IOrderService orderService;
	
	@Autowired
	@Qualifier("productServiceMysql")
	private IProductService productService;
	
	@Autowired
	@Qualifier("customerServiceMysql")
	private ICustomerService customerService;
	
	@Autowired
	@Qualifier("paymentServiceMysql")
	private IPaymentService paymentService;
	
	@GetMapping("/compra/nueva/orden")
	public String getNuevaCompraPage(Model model) {
		
		model.addAttribute("order", orderService.getOrder());
		model.addAttribute("customer", customerService.getCustomer());
		model.addAttribute("customers", customerService.listaCustomerSeleccionado());
		
		return "alta_order";
	}
	
	//busca el cliente que realiza la compra
	@GetMapping("/compra/busqueda/customer")
	public String getBuscarCustomerConFiltro(@RequestParam(value = "customerNumber")Long customerNumber, Model model) {
		LOGGER.info("METODO - - BUSCAR");
		model.addAttribute("order", orderService.getOrder());
		model.addAttribute("customer", customerService.getCustomer());		
		model.addAttribute("customerSeleccionado", customerService.buscarCustomerPorCustomerNumber(customerNumber));
		LOGGER.info("METODO - - BUSCAR - - PASO");
		
		return "alta_order";
	}
	
	@PostMapping("/compra/guardar/order")
	public ModelAndView agregarCompraPage(@ModelAttribute("order")Order order) {
		ModelAndView model = new ModelAndView("alta_compra");
		
		order.setCustomer(customerService.listaCustomerSeleccionado().get(0));
		order.setOrderDate(LocalDate.now());
		order.setRequiredDate(LocalDate.now());
		
		orderService.agregarOrder(order);
		
		model.addObject("product", productService.getProduct());
		model.addObject("productosSeleccionados", productService.listaDeProductos());
		model.addObject("orderDetail", orderDetailService.getOrderDetail());
		    
		return model;
	}
	
	@PostMapping("/compra/guardar")
	public ModelAndView agregarProductoAOrderPage(@ModelAttribute("orderDetail")OrderDetail orderDetail) {
		ModelAndView model = new ModelAndView("alta_compra");
		LOGGER.info("entro a añadir");
		OrderDetailsID id = new OrderDetailsID(orderService.obtenerOrder().get(orderService.obtenerOrder().size()-1), productService.listaDeProductos().get(0));
		LOGGER.info("paso la asignacion del ID");
		orderDetail.setId(id);
		orderDetail.setPriceEach(productService.listaDeProductos().get(0).getMSRP());
		int i = Math.toIntExact(orderService.obtenerOrder().get(orderService.obtenerOrder().size()-1).getOrderNumber());
		orderDetail.setOrderLineNumber(i);
		
		orderDetailService.agregarOrderDetail(orderDetail);
		
		model.addObject("product", productService.getProduct());
		model.addObject("productosSeleccionados", productService.listaDeProductos());
		model.addObject("orderDetail", orderDetailService.getOrderDetail());
		    
		return model;
	}
	
	@GetMapping("/compra/busqueda")
	public String getBuscarProductoConFiltro(@RequestParam(value = "productCode")Long productCode, Model model) {
		LOGGER.info("METODO - - BUSCAR");
		model.addAttribute("product", productService.getProduct()); 
		model.addAttribute("orderDetail", orderDetailService.getOrderDetail());		
		model.addAttribute("productosSeleccionados", productService.buscarProductPorProductCode(productCode));
		LOGGER.info("METODO - - BUSCAR - - PASO");
		
		return "alta_compra";
	}
	
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
		
		model.addAttribute("orderDetail", orderDetailService.buscarProductosPorOrderNumber(orderDetailService.obtenerOrderDetails().get(orderDetailService.obtenerOrderDetails().size()-1).getOrderLineNumber()));
		model.addAttribute("total", totalAPagar);
		
		return "carrito_compra";
	}
}
