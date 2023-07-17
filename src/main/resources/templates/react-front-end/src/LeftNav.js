import styles from "./Left.module.css"
import { UserContext } from "./UserContext";
import { useContext } from "react";

function LeftNav(){
  const userContext = useContext(UserContext);
  // console.log("left nav")
  // console.log(userContext)
    return(
    <>


    <div className="Left   "> 
    <div className={styles.content}>
    <div className="accordion " id="accordionPanelsStayOpenExample">

  <div style={{height:"19rem"}}  className="overflow-auto border-1 border-end" >
      
      <ol style={{listStyle:"none"}} className=" ps-3 pe-3 pt-2 ">
      {userContext.postcontext!=undefined &&Array.prototype.map.call(userContext.postcontext,(x)=>{
      return <li 
       className={`bg-light mb-2 pb-2 pt-2 position-relative  ${styles.listItem}`}
        >
      <a className={`stretched-link  ${styles.related}`}   style={{textDecoration:"none",color:"black",textTransform: "capitalize",fontSize:"14px"}} key={x.id} href={x.slug}>{ x.slug}</a>
     </li> 
    })}
    </ol>
  
  </div>



</div>
      
    </div>

 
    </div>
        
    </>
    
    );
    }
    export default LeftNav;