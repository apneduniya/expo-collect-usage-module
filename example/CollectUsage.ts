import { NativeModules } from 'react-native';

const { CollectUsage } = NativeModules;


export function startCollectionService() {
    CollectUsage.startCollectionService()
        .then(() => {
            console.log("Accessibility service started");
        })
        .catch((err: any) => {
            console.error("Error starting service: ", err);
        });;
}

export function stopCollectionService() {
    CollectUsage.stopCollectionService()
        .then(() => {
            console.log("Accessibility service stopped");
        })
        .catch((err: any) => {
            console.error("Error stopping service: ", err);
        });
}
