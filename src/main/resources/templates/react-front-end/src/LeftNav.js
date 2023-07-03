import styles from "./Left.module.css"
import { UserContext } from "./UserContext";
import { useContext } from "react";

function LeftNav(){
  const userContext = useContext(UserContext);
  // console.log("left nav")
  // console.log(userContext)
    return(
    <>
    <div className="Left "> 
    <div className={styles.content}>
    <div className="accordion " id="accordionPanelsStayOpenExample">
  <div className="accordion-item">
    <h2 className="accordion-header" id="panelsStayOpen-headingOne">
      <button className="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#panelsStayOpen-collapseOne" aria-expanded="true" aria-controls="panelsStayOpen-collapseOne">
        Intro 
      </button>
    </h2>
  
    <div id="panelsStayOpen-collapseOne" className="accordion-collapse collapse show" aria-labelledby="panelsStayOpen-headingOne">
      <div className="accordion-body">
      <ol>
      {userContext.postcontext!=undefined &&Array.prototype.map.call(userContext.postcontext,(x)=>{
      return <li><a key={x.id} href={x.slug}>{ x.slug}</a>
     </li> 
    })}
    </ol>
      </div>
    </div>
  </div>



</div>
      
    </div>

 
    </div>
        
    </>
    
    );
    }
    export default LeftNav;