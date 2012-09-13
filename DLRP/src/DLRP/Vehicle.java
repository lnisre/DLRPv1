/**
 * @author ������
 */
package DLRP;

import java.util.ArrayList;
import java.util.List;

import DLRP.Vertex.AdjacentVertex;

public class Vehicle {
	
	int ID;
	int Capacity;
	int Remainder; // ����ʣ��ռ�
	
	// ��ʱֻ���ǳ�����ǰλ�ú���һ���ڵ�λ��
	Vertex currentVertex;
	Vertex nextStep;
	Vertex lastStep;
	
	// �������о��룬�������г������ٶ�һ��
	int runningDistance;
	List<Package> packageList;
	/**
	 * show ������ʼ��
	 * @param ID ����ID
	 * @param Capacity ��������
 	 * @param origin ������ʼ��
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
	 * show �ҳ�������ǰ�ڵ��Ŀ�Ľڵ�destion֮������·��
	 * @param destion Ŀ�Ľڵ�
	 * @return [0]���·���ľ����[1]��һ�ڵ�ID
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
		
		// initDistance()���ؾ��ʼ������Ľڵ�ID
		int tmp = initDistance(visted, distance, queue, route, vertexList);
		if (vertexList.get(tmp).equals(destion)) {
			// �����ѷ��ʵ�Ŀ�Ľڵ㣬����·��
			
//			// ����
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
	 * show ����������һ�ڵ㣬���ó����������ذ����ĵ�ǰ�ڵ㣬ж�س����ϵ����а���
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
	 * show ����װ�ذ�����ʣ��ռ�-1
	 * @param pack ����
	 * @return true ��������ʣ��ռ� ��false ��������
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
