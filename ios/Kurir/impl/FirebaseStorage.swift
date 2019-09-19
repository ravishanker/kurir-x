//
//  DatabaseImpl.swift
//  Kurir
//
//  Created by Loren on 1/9/19.
//  Copyright © 2019 Crazybean Studio. All rights reserved.
//

import Mobilex
import Firebase

class FirebaseStorage: CloudStorage {
    private lazy var firestore: Firestore = {
        let firestore = Firestore.firestore()
        let settings = firestore.settings
        settings.areTimestampsInSnapshotsEnabled = true
        firestore.settings = settings
        return firestore
    }()
    
    func readData(paths: String, completion: @escaping ([String : Any]?, KotlinThrowable?) -> Void) {
        firestore.document(paths)
            .getDocument { snapshot, error in
                completion(snapshot?.data(), error?.throwable)
        }
    }
    
    func writeData(paths: String, payload: [String : Any], completion: @escaping (KotlinBoolean, KotlinThrowable?) -> Void) {
        firestore.document(paths)
            .setData(payload) { error in
                completion((error == nil).boolean, error?.throwable)
        }
    }
    
    func delete(paths: String, completion: @escaping (KotlinBoolean, KotlinThrowable?) -> Void) {
        firestore.document(paths)
            .delete { error in
                completion((error == nil).boolean, error?.throwable)
        }
    }
    
    func readArray(paths: String, completion: @escaping ([[String : Any]]?, KotlinThrowable?) -> Void,
                   observation: (([[String : Any]]?, [[String : Any]]?, [[String : Any]]?, KotlinThrowable?) -> Void)? = nil) {
        firestore.collection(paths)
            .getDocuments { snapshot, error in
                if let snapshot = snapshot {
                    completion(snapshot.documents.map{ $0.data() }, error?.throwable)
                } else {
                    completion(nil, error?.throwable)
                }
        }
    }
}

extension Error {
    var throwable: KotlinThrowable {
        get {
            return KotlinThrowable(message: localizedDescription)
        }
    }
}

extension Bool {
    var boolean: KotlinBoolean {
        get {
            return KotlinBoolean(value: self)
        }
    }
}

