/**
 * @author ������
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
	List<Vertex> shortestRouting; // ���������·��
	
	/**
	 * ��ʼ��������Ϣ
	 * @param ID ����ID
	 * @param name ��������
	 * @param size ������С
	 * @param origin ������ʼ��
	 * @param destion ����Ŀ�ĵ�
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
	 * show ���ذ�����ʼ�㵽Ŀ�ĵ�����·��
	 * @return ���·��
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
	 * show �����·����ɾ����vertex����֮ǰ�Ľڵ�
	 * @param vertex ������ǰ�ڵ�
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
	 * show Ѱ�ҴӰ�����㵽���յ�����·��
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
		
		// initDistance()���ؾ��ʼ������Ľڵ�ID
		int tmp = initDistance(visted, distance, queue, route, vertexList);
		if (vertexList.get(tmp).equals(destion)) {
			// �����ѷ��ʵ�Ŀ�Ľڵ㣬����·��
			
//			// ����
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
			// ��list�����У����������remove����
			boolean[] adjacentAllVistedArray = new boolean[queue.size()];
			int index = 0;
			
			for (Vertex vertex: queue) {
				
				minDisArray[index] = 999;
				//
				minIDArray[index] = vertex.getVertexID();
				adjacentAllVistedArray[index] = true;
				proVertexID[index] = vertex.getVertexID();
				
				// �򵥵�ʹ��list���ƴ�listΪ���list
				// ��������һ���ڵ���ڽӽڵ��visited[]����true���򽫴˽ڵ�����list���޳�
				List<AdjacentVertex> adjacent = vertex.getAdjacentVertex();
				
				for (AdjacentVertex adjacentVertex : adjacent) {
					// ���ڽӵ��Ѿ����ʹ�
					if (visted[adjacentVertex.getVertexID()] == true) {
						continue;
					}
					adjacentAllVistedArray[index] = false;
					if (adjacentVertex.getDistance() < minDisArray[index]) {
						minIDArray[index] = adjacentVertex.getVertexID();
						minDisArray[index] = adjacentVertex.getDistance();
					}
				}
				// p���ڽӽڵ���� + ָ��p�����·��
				if (adjacentAllVistedArray[index] == false) {
					minDisArray[index] = minDisArray[index] + distance[vertex.getVertexID()];
				}
				
				index += 1;
			}
				
			// ��queue��ɾ���ڽӵ��Ѿ�ȫ�����ʹ��Ľڵ�
			int delIndex = 0;
			for (int i = 0; i < queue.size(); i++) {
				if (adjacentAllVistedArray[i] == true) {
					queue.remove(delIndex);
					delIndex = delIndex - 1;
				}
				delIndex += 1;
			}
			
			int minIDIndex = minOfArray(minDisArray);
			// ����µ�δ���ʽڵ�
			queue.add(vertexList.get(minIDArray[minIDIndex]));
			
			visted[minIDArray[minIDIndex]] = true;
			distance[minIDArray[minIDIndex]] = minDisArray[minIDIndex];
			route[minIDArray[minIDIndex]] = proVertexID[minIDIndex];
			
			if (vertexList.get(minIDArray[minIDIndex]).equals(destion)) {
				// �����ѷ��ʵ�Ŀ�Ľڵ㣬����·��
				break;
			}
		}
		
////	// ����
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
