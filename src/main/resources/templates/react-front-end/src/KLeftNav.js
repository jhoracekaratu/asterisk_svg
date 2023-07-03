import styles from "./Left.module.css"
function LeftNav(){
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
      <ol><a href="">Installation</a></ol>
      <ol><a href="">Syntax</a></ol>
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