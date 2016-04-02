package jonathan.map;

public class Coordinate {
		private double lat;
		private double lng;
		private double ele;
		private long timestamp;
		private double distance;
		
		public Coordinate(double lat, double lng) {
			this.lat = lat;
			this.lng = lng;
		}

		public Coordinate() {
			// TODO Auto-generated constructor stub
		}

		public double getLat() {
			return lat;
		}

		public void setLat(double lat) {
			this.lat = lat;
		}

		public double getLng() {
			return lng;
		}

		public void setLng(double lng) {
			this.lng = lng;
		}

		public long getTimestamp() {
			return timestamp;
		}

		public void setTimestamp(long timestamp) {
			this.timestamp = timestamp;
		}

		public double getDistance() {
			return distance;
		}

		public void setDistance(double distance) {
			this.distance = distance;
		}
		
		public double getEle() {
			return ele;
		}

		public void setEle(double ele) {
			this.ele = ele;
		}

		public String toString() {
			return "[lat:"+String.format("%3.8f", lat)+", lng:"+String.format("%3.8f", lng)+"]";
		}
		
}
