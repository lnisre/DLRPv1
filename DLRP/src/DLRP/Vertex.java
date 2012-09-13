/**
 * @author ������
 */
package DLRP;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Vertex {
	String name;
	int	ID;
	List<AdjacentVertex> adjacentVertex;
	List<Vertex> vertexList;
	List<Package> unPickUpPackageLis;
	
	/**
	 * ��ʼ���������ڵ���Ϣ
	 * @param ID �ڵ�ID
	 * @param name �ڵ�����
	 * @param adjacentVertex �ڽӽڵ�ID
	 * @param distance �ڽӽڵ����
	 */
	public Vertex(int ID, String name, int[] adjacentVertex, int[] distance) {
		// TODO Auto-generated constructor stub
		this.ID = ID;
		this.name = name;
		this.adjacentVertex = new ArrayList<Vertex.AdjacentVertex>();
		for (int i = 0; i < adjacentVertex.length; i++) {
			// AdjacentVertex����Ƕ�ĸ�����
			this.adjacentVertex.add(new AdjacentVertex(adjacentVertex[i], distance[i]));
		}
		this.vertexList = null;
		this.unPickUpPackageLis = new ArrayList<Package>();
	}
	
	public int getVertexID() {
		return this.ID;
	}
	
	public String getVertexName() {
		return this.name;
	}
	
	public void setVertexList(List<Vertex> list) {
		this.vertexList = list;
	}
	
	public List<Vertex> getVertexList() {
		return this.vertexList;
	}
	
	/**
	 * show ��ڵ���Ӱ���
	 * @param pack ����
	 */
	public void addUnPickUpPackage(Package pack) {
		this.unPickUpPackageLis.add(pack);
	}
	
	/**
	 * show ɾ�����ڵ����
	 * @param packIterator ���������еĵ�����
	 */
	public void delUnPickUpPackage(Iterator<Package> packIterator) {
		packIterator.remove();
	}
	
	public List<Package> getUnPickUpPackage() {
		return this.unPickUpPackageLis;
	}
	
	public boolean hasUnPickPackage() {
		return !this.unPickUpPackageLis.isEmpty();
	}
	
	public int getPackageCount() {
		return this.unPickUpPackageLis.size();
	}
	
	/**
	 * show ��ȡ�ڽӽڵ�İ�����Ϣ
	 * @return [i][0]: �ڵ�ID [i][1]: �ڽӽڵ������ [i][2]: �ڽӽڵ����
	 */
	public double[][] askAdjacentPackages() {
		double[][] adjacentPackages = new double[this.adjacentVertex.size()][3];
		for (int i = 0; i < this.adjacentVertex.size(); i++) {
			adjacentPackages[i][0] = this.adjacentVertex.get(i).getVertexID();
			Vertex adjacent = this.vertexList.get(this.adjacentVertex.get(i).getVertexID());
			adjacentPackages[i][1] = adjacent.getPackageCount();
			adjacentPackages[i][2] = this.adjacentVertex.get(i).getDistance();
		}
		return adjacentPackages;
	}

	/**
	 * show ��ȡ��ǰ�ڵ�İ�����Ϣ
	 * @return [i][0]: �ڵ�ID  [i][1]: ���ڵ�������ص��ڽӽڵ�İ�����    [i][2]: �ڽӽڵ����
	 */
	public double[][] askCurrentVertexPackages() {
		double[][] currentVertexPackage = new double [this.adjacentVertex.size()][3];
		for (int i = 0; i< this.adjacentVertex.size(); i++) {
			currentVertexPackage[i][0] = this.adjacentVertex.get(i).getVertexID();
			Vertex adjacent = this.vertexList.get(this.adjacentVertex.get(i).getVertexID());
			for (Package pack : this.unPickUpPackageLis) {
				if (pack.getShortestRouting().contains(adjacent)) {
					currentVertexPackage[i][1] ++;
				}
			}
			currentVertexPackage[i][2] = this.adjacentVertex.get(i).getDistance();
		}
		return currentVertexPackage;
	}
	
	public int getAdjacnetCount() {
		return this.adjacentVertex.size();
	}
	public List<Package> viewUnPickageList() {
		return this.unPickUpPackageLis;
	}
	
	public List<AdjacentVertex> getAdjacentVertex() {
		return this.adjacentVertex;
	}
	
	// ���帨���࣬�ڽ��ڵ�ID�� ���ڽ��ڵ�����
	class AdjacentVertex {
		private int vertexID;
		private int distance;
		
		public AdjacentVertex(int ID, int dist) {
			this.vertexID = ID;
			this.distance = dist;
		}
		
		public int getVertexID() {
			return this.vertexID;
		}
		
		public int getDistance() {
			return this.distance;
		}
	}
}
