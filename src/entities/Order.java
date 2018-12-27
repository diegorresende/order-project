package entities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entities.enums.Status;

public class Order {
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	
	private Date moment;
	private Status status;
	private List<OrderItem> orderItems = new ArrayList<>();
	private Client client;
	
	public Order(Date moment, Status status, Client client) {
		this.moment = moment;
		this.status = status;
		this.client = client;
	}

	public Date getMoment() {
		return moment;
	}

	public void setMoment(Date moment) {
		this.moment = moment;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	
	public void addOrder(OrderItem order) {
		orderItems.add(order);
	}
	
	public void removeOrder(OrderItem order) {
		orderItems.remove(order);
	}
	
	public double total() {
		double sum = 0.0;
		for(OrderItem item:orderItems) {
			sum += item.subTotal();
		}
		return sum;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Order moment: "+sdf.format(moment) + "\n");
		sb.append("Order status: "+ status + "\n");
		sb.append("Client: "+ client.getName()+" "
		+"("+client.getBithDate()+")"+" - "+ client.getEmail()+"\n");
		
		sb.append("Order items:"+"\n");
		for(OrderItem o:orderItems) {
			sb.append(o.getProduct().getName()+", $"
			+o.getPrice()+", Quantity: "
			+o.getQuantity()+" Subtotal: $"
			+o.subTotal()+"\n");	
		}
		sb.append("Total: $"+(String.format("%.2f", total())));
		return sb.toString();
		
	}
}
