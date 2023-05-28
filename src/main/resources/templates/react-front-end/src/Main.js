import styles from "./Main.module.css"
function Main(){
    return(
    <>
    <div className={`Main`} >

    <div className={styles.content}>

    {/* let content be in fom of pill form from the start.
     From admin, put markers to seperate as pills with zebra coloring */}
     <div className={`${styles.pills}`} >
     <h1 className={`${styles.header}`}>Java Threads</h1>

    <p className={`${styles.body}`}>Threads assist to handle more than one task simulataneously.</p>
     </div>
    
    </div>
    
    </div>
        
    </>
    
    );
    }
    export default Main;