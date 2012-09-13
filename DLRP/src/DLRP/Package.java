/**
 * @author 高文龙
 */
package DLRP;

import java.util.ArrayList;
import java.util.List;

import DLRP.Vertex.AdjacentVertex;

public class Package {
	int ID;
	String name;
	int size;
	int distance;
	boolean isPickUp;
	Vertex origin;
	Vertex destion;
	Vertex currentVertex;
	List<Vertex> shortestRouting; // 包裹的最短路径
	
	/**
	 * 初始化包裹信息
	 * @param ID 包裹ID
	 * @param name 包裹名称
	 * @param size 包裹大小
	 * @param origin 包裹起始点
	 * @param destion 包裹目的点
	 */
	public Package(int ID, String name, int size, Vertex origin, Vertex destion) {
		// TODO Auto-generated constructor stub
		this.ID = ID;
		this.name = name;
		this.size = size;
		this.origin = origin;
		this.currentVertex = origin;
		this.destion = destion;
		isPickUp = false;
		this.shortestRouting = new ArrayList<Vertex>();
		
		this.distance = findShortestRoute();
	}
	
	/**
	 * show 返回包裹起始点到目的点的最短路径
	 * @return 最短路径
	 */
	public List<Vertex> getShortestRouting() {
		return this.shortestRouting;
	}
	
	public int getPackageID() {
		return this.ID;
	}
	
	public String getPackageName() {
		return this.name;
	}
	
	public int getShortestDistance() {
		return this.distance;
	}
	
	public Vertex getDestion() {
		return this.destion;
	}
	
	/**
	 * show 从最短路径中删除掉vertex及其之前的节点
	 * @param vertex 包裹当前节点
	 */
	public void delVertexOfShortesting(Vertex vertex) {
		int index = this.shortestRouting.indexOf(vertex);
		for ( ; index > 0; index--) {
			this.shortestRouting.remove(0);
		}
	}
	
	public Vertex getCurrentVertex() {
		return this.currentVertex;
	}
	
	public void arriveVertex(Vertex vertex) {
		this.currentVertex = vertex;
	}
	
	/**
	 * show 寻找从包裹起点到接收点的最短路径
	 */
	public int findShortestRoute() {
		List<Vertex> vertexList = this.origin.getVertexList();
		boolean[] visted = new boolean[vertexList.size()];
		int[] distance = new int[vertexList.size()];
		int[] route = new int[vertexList.size()];

		for (int i = 0; i < vertexList.size(); i++) {
			visted[i] = false;
			distance[i] = 9999;
		}
		
		visted[origin.getVertexID()] = true;
		distance[origin.getVertexID()] = 0;
		
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
			this.shortestRouting.add(destion);
			return distance[destion.getVertexID()];
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
//		System.out.println("PackageName: " + this.name);
//		System.out.println("S: " + " ID: " + this.origin.getVertexID()
//				+ " name: " + this.origin.getVertexName());
//		System.out.println("destination: " + " ID: " + destion.getVertexID() + " distance: " + distance[destion.getVertexID()]
//		                               + " name: " + destion.getVertexName());
//		for (int i = route[destion.getVertexID()]; i != this.origin.getVertexID(); 
//			i = route[i]) {
//			System.out.println(i + " " + vertexList.get(i).getVertexName());
//		}
//		System.out.print("\n\n");
////				//
		
		int dist = distance[destion.getVertexID()];
		for (int i = route[destion.getVertexID()]; i != this.origin.getVertexID(); 
			i = route[i]) {
			this.shortestRouting.add(0, vertexList.get(i));
		}
		this.shortestRouting.add(destion);
		
		return dist;
	}
	
	private int initDistance(boolean[] visted, int[] distance,
			List<Vertex> queue, int[] route, List<Vertex> vertexList) {
		int minDis = 999;
		int minID = 0;
		List<AdjacentVertex> adjacent = this.origin.getAdjacentVertex();
		for (AdjacentVertex adjacentVertex : adjacent) {
			if (adjacentVertex.getDistance() < minDis) {
				minDis = adjacentVertex.getDistance();
				minID = adjacentVertex.getVertexID();
			}
			distance[adjacentVertex.getVertexID()] = adjacentVertex.getDistance();
		}
		queue.add(this.origin);
		route[this.origin.getVertexID()] = this.origin.getVertexID();
		
		queue.add(vertexList.get(minID));
		route[vertexList.get(minID).getVertexID()] = this.origin.getVertexID();
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
}
