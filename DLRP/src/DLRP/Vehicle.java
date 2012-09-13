/**
 * @author 高文龙
 */
package DLRP;

import java.util.ArrayList;
import java.util.List;

import DLRP.Vertex.AdjacentVertex;

public class Vehicle {
	
	int ID;
	int Capacity;
	int Remainder; // 车辆剩余空间
	
	// 暂时只考虑车辆当前位置和下一步节点位置
	Vertex currentVertex;
	Vertex nextStep;
	Vertex lastStep;
	
	// 车辆运行距离，假设所有车辆的速度一致
	int runningDistance;
	List<Package> packageList;
	/**
	 * show 车辆初始化
	 * @param ID 车辆ID
	 * @param Capacity 车辆容量
 	 * @param origin 车辆起始点
	 */
	public Vehicle(int ID, int Capacity, Vertex origin) {
		// TODO Auto-generated constructor stub
		this.ID = ID;
		this.Capacity = Capacity;
		this.Remainder = Capacity;
		this.currentVertex = origin;
		this.nextStep = null;
		this.lastStep = origin;
		this.runningDistance = 0;
		this.packageList = new ArrayList<Package>();
	}
	
	public int getVehicleID() {
		return this.ID;
	}
	
	public int getLoadingSize() {
		return this.Capacity - this.Remainder;
	}
	
	public Vertex getCurrentVertex() {
		return this.currentVertex;
	}
	
	public Integer getRunningDistance() {
		return this.runningDistance;
	}
	
	public void setNextStep(Vertex nextStep) {
		this.nextStep = nextStep;
	}
	
	/**
	 * show 找出车辆当前节点和目的节点destion之间的最短路径
	 * @param destion 目的节点
	 * @return [0]最短路径的距离和[1]下一节点ID
	 */
	public int[] findShortestRoutingFromCurrent(Vertex destion) {
		int[] shortestAndID = new int[2];
		
		List<Vertex> vertexList = this.currentVertex.getVertexList();
		boolean[] visted = new boolean[vertexList.size()];
		int[] distance = new int[vertexList.size()];
		int[] route = new int[vertexList.size()];

		for (int i = 0; i < vertexList.size(); i++) {
			visted[i] = false;
			distance[i] = 9999;
		}
		
		visted[this.currentVertex.getVertexID()] = true;
		distance[this.currentVertex.getVertexID()] = 0;
		
		List<Vertex> queue = new ArrayList<Vertex>();
		
		// initDistance()返回距初始点最近的节点ID
		int tmp = initDistance(visted, distance, queue, route, vertexList);
		if (vertexList.get(tmp).equals(destion)) {
			// 表明已访问到目的节点，返回路径
			
//			// 测试
//			System.out.println("S: " + " ID: " + this.vehicle.getOriginID() + " name: " + this.vertexs.get(this.vehicle.getOriginID()).getVertexName());
//			System.out.println("destination: " + " ID: " + min.getVertexID() + " distance: " + distance[min.getVertexID()]
//			                            + " name: " + min.getVertexName());
//			//
			shortestAndID[0] = distance[destion.getVertexID()];
			shortestAndID[1] = destion.getVertexID();
			return shortestAndID;
		}
		
		while (queue.isEmpty() != true) {
			int[] minDisArray = new int[queue.size()];
			int[] minIDArray = new int[queue.size()];
			int[] proVertexID = new int[queue.size()];
			// 在list遍历中，不方便进行remove操作
			boolean[] adjacentAllVistedArray = new boolean[queue.size()];
			int index = 0;
			
			for (Vertex vertex: queue) {
				
				minDisArray[index] = 999;
				//
				minIDArray[index] = vertex.getVertexID();
				adjacentAllVistedArray[index] = true;
				proVertexID[index] = vertex.getVertexID();
				
				// 简单的使用list，称此list为外层list
				// 若其中有一个节点的邻接节点的visited[]都是true，则将此节点从外层list中剔除
				List<AdjacentVertex> adjacent = vertex.getAdjacentVertex();
				
				for (AdjacentVertex adjacentVertex : adjacent) {
					// 此邻接点已经访问过
					if (visted[adjacentVertex.getVertexID()] == true) {
						continue;
					}
					adjacentAllVistedArray[index] = false;
					if (adjacentVertex.getDistance() < minDisArray[index]) {
						minIDArray[index] = adjacentVertex.getVertexID();
						minDisArray[index] = adjacentVertex.getDistance();
					}
				}
				// p的邻接节点距离 + 指向p的最短路径
				if (adjacentAllVistedArray[index] == false) {
					minDisArray[index] = minDisArray[index] + distance[vertex.getVertexID()];
				}
				
				index += 1;
			}
				
			// 从queue中删除邻接点已经全部访问过的节点
			int delIndex = 0;
			for (int i = 0; i < queue.size(); i++) {
				if (adjacentAllVistedArray[i] == true) {
					queue.remove(delIndex);
					delIndex = delIndex - 1;
				}
				delIndex += 1;
			}
			
			int minIDIndex = minOfArray(minDisArray);
			// 添加新的未访问节点
			queue.add(vertexList.get(minIDArray[minIDIndex]));
			
			visted[minIDArray[minIDIndex]] = true;
			distance[minIDArray[minIDIndex]] = minDisArray[minIDIndex];
			route[minIDArray[minIDIndex]] = proVertexID[minIDIndex];
			
			if (vertexList.get(minIDArray[minIDIndex]).equals(destion)) {
				// 表明已访问到目的节点，返回路径
				break;
			}
		}
		
////	// 测试
//		System.out.println(" ID: " + this.currentVertex.getVertexID()
//				+ " name: " + this.currentVertex.getVertexName());
//		System.out.println("destination: " + " ID: " + destion.getVertexID() + " distance: " + distance[destion.getVertexID()]
//		                               + " name: " + destion.getVertexName());
//		for (int i = route[destion.getVertexID()]; i != this.currentVertex.getVertexID(); 
//			i = route[i]) {
//			System.out.println(i + " " + vertexList.get(i).getVertexName());
//		}
//		System.out.print("\n\n");
////				//
		
		for (int i = route[destion.getVertexID()]; i != this.currentVertex.getVertexID(); 
			i = route[i]) {
//			this.shortestRouting.add(0, vertexList.get(i));
			shortestAndID[0] = distance[vertexList.get(i).getVertexID()];
			shortestAndID[1] = vertexList.get(i).getVertexID();
		}
		
		return shortestAndID;
	}
	

	private int initDistance(boolean[] visted, int[] distance,
			List<Vertex> queue, int[] route, List<Vertex> vertexList) {
		int minDis = 999;
		int minID = 0;
		List<AdjacentVertex> adjacent = this.currentVertex.getAdjacentVertex();
		for (AdjacentVertex adjacentVertex : adjacent) {
			if (adjacentVertex.getDistance() < minDis) {
				minDis = adjacentVertex.getDistance();
				minID = adjacentVertex.getVertexID();
			}
			distance[adjacentVertex.getVertexID()] = adjacentVertex.getDistance();
		}
		queue.add(this.currentVertex);
		route[this.currentVertex.getVertexID()] = this.currentVertex.getVertexID();
		
		queue.add(vertexList.get(minID));
		route[vertexList.get(minID).getVertexID()] = this.currentVertex.getVertexID();
		visted[minID] = true;
		return minID; 
	}
	
	private int minOfArray(int[] minArray) {
		int min = 999;
		int index = 0;
		for (int i = 0; i < minArray.length; i++) {
			if (minArray[i] < min) {
				min = minArray[i];
				index = i;
			}
		}
		return index;	
	}
	
	public void running() {
		List<AdjacentVertex> adjacent = this.currentVertex.getAdjacentVertex();
		for (AdjacentVertex adjacentVertex : adjacent) {
			if (adjacentVertex.getVertexID() == this.nextStep.getVertexID()) {
				this.runningDistance += adjacentVertex.getDistance();
				break;
			}
		}
	}
	
	/**
	 * show 车辆到达下一节点，设置车辆及其运载包裹的当前节点，卸载车辆上的所有包裹
	 */
	public void arriveVertex() {
		this.lastStep = this.currentVertex;
		this.currentVertex = this.nextStep;
		for (Package pack : this.packageList) {
			if (pack.getDestion().equals(this.currentVertex)){
				continue;
			}
			this.currentVertex.addUnPickUpPackage(pack);
			pack.arriveVertex(this.nextStep);
		}
		this.packageList.clear();
		this.Remainder = this.Capacity;
	}
	
	/**
	 * show 车辆装载包裹，剩余空间-1
	 * @param pack 包裹
	 * @return true 车辆仍有剩余空间 ；false 车辆已满
	 */
	public boolean pickUpPackage(Package pack) {
		if (this.Remainder > 0) {
			this.Remainder--;
			this.packageList.add(pack);
			return true;
		}
		return false;
	}
	
	public boolean isLastStep(Vertex vertex) {
		if (this.lastStep.equals(vertex)) {
			return true;
		}
		return false;
	}
}
