package aresit.com.photoapp.data;

public enum  ClassifierProvider {
    EINSTEINAI {
        public String toString() {
            return "Einstein API";
        }
    },

    GOOGLEAPI

    {
        public String toString() {
            return "Google Cloud AI";
    }
    },

    LOCALAPI {
        @Override
        public String toString() {
            return "Local API";
        }
    }

}
