//
//  DatabaseImpl.swift
//  Kurir
//
//  Created by Loren on 1/9/19.
//  Copyright © 2019 Crazybean Studio. All rights reserved.
//

import Mobilex
import Firebase

class DatabaseImpl: Database {
    private lazy var firestore: Firestore = {
        let firestore = Firestore.firestore()
        let settings = firestore.settings
        settings.areTimestampsInSnapshotsEnabled = true
        firestore.settings = settings
        return firestore
    }()
    
    func readData(tableName: String, filters: [String : Any]?, onSuccess: @escaping ([[String : Any]]) -> Void, onError: @escaping (KotlinThrowable) -> Void) {
        let reference = firestore.collection(tableName)
        if let filters = filters {
            var query: Query? = reference
            for filter in filters {
                query = query?.whereField(filter.key, isEqualTo: filter.value)
            }
            query?.getDocuments { snapshot, error in
                if let error = error {
                    onError(error.throwable)
                } else {
                    let result = snapshot!.documents.map{ $0.data() }
                    onSuccess(result)
                }
            }
        } else {
            reference.getDocuments { snapshot, error in
                    if let error = error {
                        onError(error.throwable)
                    } else {
                        let result = snapshot!.documents.map{ $0.data() }
                        onSuccess(result)
                    }
            }
        }
    }
    
    func writeData(tableName: String, filters: [String : Any]?, payload: [String : Any], onSuccess: @escaping (String) -> Void, onError: @escaping (KotlinThrowable) -> Void) {
        if let identifier = filters?["id"] as? String {
            firestore.collection(tableName)
                .document(identifier)
                .setData(payload) { error in
                    if let error = error {
                        onError(error.throwable)
                    } else {
                        onSuccess(identifier)
                    }
            }
        } else {
            var ref: DocumentReference? = nil
            ref = firestore.collection(tableName)
                .addDocument(data: payload) { error in
                    if let error = error {
                        onError(error.throwable)
                    } else {
                        if let documentId = ref?.documentID {
                            onSuccess(documentId)
                        } else {
                            onError(KotlinThrowable(message: "Unknown error"))
                        }
                    }
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
