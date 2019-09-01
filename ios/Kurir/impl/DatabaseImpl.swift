//
//  DatabaseImpl.swift
//  Kurir
//
//  Created by Loren on 1/9/19.
//  Copyright © 2019 Crazybean Studio. All rights reserved.
//

import Foundation
import Mobilex
import Firebase

class DatabaseImpl: Database {
    private lazy var firestore: Firestore = {
        FirebaseApp.configure()
        return Firestore.firestore()
    }()
    
    func readData(tableName: String, onSuccess: @escaping ([[String : Any]]) -> Void, onError: ((KotlinThrowable) -> Void)? = nil) {
        firestore.collection(tableName)
            .getDocuments { snapshot, error in
                if let error = error {
                    let throwable = KotlinThrowable(message: error.localizedDescription)
                    onError?(throwable)
                } else {
                    let result = snapshot!.documents.map{ $0.data() }
                    onSuccess(result)
                }
        }
    }
    
    func writeData(tableName: String, payload: [String : Any], onSuccess: @escaping (String) -> Void, onError: ((KotlinThrowable) -> Void)? = nil) {
        var ref: DocumentReference? = nil
        ref = firestore.collection(tableName)
            .addDocument(data: payload) { error in
                if let error = error {
                    let throwable = KotlinThrowable(message: error.localizedDescription)
                    onError?(throwable)
                } else {
                    if let documentId = ref?.documentID {
                        onSuccess(documentId)
                    } else {
                        onError?(KotlinThrowable(message: "Unknown error"))
                    }
                }
        }
    }
}
