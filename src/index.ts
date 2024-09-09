import CollectUsageModule from './CollectUsageModule';


function startCollectionService() {

    console.log("hello");

    CollectUsageModule.startCollectionService()
        .then(() => {
            console.log("Accessibility service started");
        })
        .catch((err: any) => {
            console.error("Error starting service: ", err);
        });;
}

function stopCollectionService() {

    console.log("world");

    CollectUsageModule.stopCollectionService()
        .then(() => {
            console.log("Accessibility service stopped");
        })
        .catch((err: any) => {
            console.error("Error stopping service: ", err);
        });
}


export { startCollectionService, stopCollectionService };
